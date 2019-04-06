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
  'allReminders': function (browser) {
    utils(browser).allReminders();
  },
  'sendReminder': function (browser) {
    utils(browser).sendReminder();
  },
  'checkStudentReminders': function (browser) {
    utils(browser).allStudents();
    utils(browser).clickStudent();
    utils(browser).clickStudentReminders();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };