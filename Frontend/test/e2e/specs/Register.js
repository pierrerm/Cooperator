var utils = require('../utils');

module.exports = {
  '@tags' : ['register'],
  before: function (browser) {
    utils(browser).openBrowser();
  },
  'goToRegister': function (browser) {
    utils(browser).clickRegister();
  },
  'register': function (browser) {  
    utils(browser).registerNewUser();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };