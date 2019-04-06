var utils = require('../utils');

module.exports = {
  '@tags' : ['viewAllStudents'],
  before: function (browser) {
    utils(browser).openBrowser();
  },
  'goToLogin': function (browser) {
    utils(browser).clickLogin();
  },
  'signIn': function (browser) {
    utils(browser).signIn();
  },
  'allStudents': function (browser) {
    utils(browser).allStudents();
  },
  'clickOnStudent': function (browser) {
    utils(browser).clickStudent();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };