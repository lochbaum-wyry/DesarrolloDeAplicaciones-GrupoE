'use strict';
var trans = [] ; 

function getTranslations(language) {
  var origLang = language; 

  var result = {} ; 

  for (var ctrl in trans) {
    if ( ! trans[ctrl][origLang] ) {
      language = 'en'; 
    } else {
      language = origLang; 
    }

    for (var id in trans[ctrl][language]) {
      result[id] = trans[ctrl][language][id];
    }
  }
  return result; 
}