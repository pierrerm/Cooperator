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
  'allStudents': function (browser) {
    utils(browser).allStudents();
  },
  'clickOnStudent': function (browser) {
    utils(browser).clickStudent();
  },
  'clickOnCoop': function (browser) {
    utils(browser).clickCoop();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };