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
  'allEmployers': function (browser) {
    utils(browser).allEmployers();
  },
  'clickOnEmployer': function (browser) {
    utils(browser).clickEmployer();
    utils(browser).clickEmployerCoop();
    //utils(browser).clickEmployerForm();
  },
  after: function (browser) {
    browser.pause(2000);
    browser.end();
  }
  };