var utils = require('../utils');

module.exports = {
  '@tags' : ['login'],
  before: function (browser) {
    utils(browser).openBrowser();
  },
  'goToLogin': function (browser) {
    utils(browser).clickLogin();
  },
  'signIn': function (browser) {
    utils(browser).signIn();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };