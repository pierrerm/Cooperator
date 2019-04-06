var utils = require('../utils');

module.exports = {
  '@tags' : ['viewStudentCoops'],
  before: function (browser) {
    utils(browser).openBrowser();
  },
  'goToLogin': function (browser) {
    utils(browser).clickLogin();
  },
  'signIn': function (browser) {
    utils(browser).signIn();
  },
  'allStats': function (browser) {
    utils(browser).allStats();
  },
  'viewStats': function (browser) {
    utils(browser).inputStats();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };