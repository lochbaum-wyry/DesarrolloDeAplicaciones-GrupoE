(function () {
'use strict';

angular.module('desappGrupoeFrontendApp').controller('topBarCtrl', topBarCtrl);

/* @ngInject */
function topBarCtrl(SessionService,$window, NotificationService) {
  /* navBarCtrl */ var vm = this;
  vm.user = SessionService.user();

  vm.logout = logout; 

  vm.notifications = [];

  setInterval(getNotifications,15000); 

  function getNotifications() {
  	NotificationService.notificationsFor(vm.user.id).then(onGetNotif).catch(function() {}); 
  }

  function onGetNotif(notifications) {
  	vm.notifications = notifications.map(formatNotification); 
  }

  function formatNotification(notification) {
// RideRequested, RideAccepted, RideRejected, WallPost, IncomingMessage, System;
    switch (notification.type) {
      case "RideRequested": return toRideRequestedNotif(notification) ;
      case "RideAccepted": return toRideAcceptedNotif(notification) ;
      case "RideRejected": return toRideRejectedNotif(notification) ;
      case "IncomingMessage": return toMessageNotif(notification) ;
      case "WallPost": return toWallPostNotif(notification) ;
      case "System": return toSystemNotif(notification) ;

    }
  }

  function toRideRequestedNotif(notification) {

    return { 
      href: "#rideRequests",
      image: notification.rideRequest.requester.image,
      sender: notification.rideRequest.requester.name, 
      timestamp: notification.timestamp, 
      msg: "ride_request_notification"
    };
  }

  function toRideAcceptedNotif(notification) {

    return { 
      href: "#home",
      image: notification.ride.driver.image,
      sender: notification.ride.driver.name, 
      timestamp: notification.timestamp, 
      msg: "ride_accepted_notification"
    };
  }

  function toRideRejectedNotif(notification) {
    return { 
      href: "",
      image: notification.rideRequest.driver.image,
      sender: notification.rideRequest.driver.name, 
      timestamp: notification.timestamp, 
      msg: "ride_rejected_notification"
    };
  }

  function toMessageNotif(notification) {
    return { 
      href: "#chats",
      image: notification.msgFrom.image,
      sender: notification.msgFrom.name, 
      timestamp: notification.timestamp, 
      msg: "incoming_message_notification"
    };
  }

  function toWallPostNotif(notification) {
    return { 
      href: "#profile/" + vm.user.id,
      image: notification.msgFrom.image,
      sender: notification.msgFrom.name, 
      timestamp: notification.timestamp, 
      msg: "wall_post_notification"
    };
  }
  function toSystemNotif(notification) {
    return { 
      href: "",
      image: null,
      sender: null, 
      timestamp: notification.timestamp, 
      msg: notification.msg
    };


  }


  function logout() {
    SessionService.finalize();
    $window.location.href = "/index.html";
  }

} // navBarCtrl


})()