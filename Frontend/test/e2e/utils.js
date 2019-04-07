module.exports = function (browser) {

    const devServer = browser.globals.devServerURL

    this.openBrowser = function () {
        browser
        .windowMaximize()
        .url(devServer)
        .waitForElementVisible('body', 1000);
        return browser;
    };
    this.clickRegister = function () {
        browser
        .waitForElementVisible('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(5)', 5000)
        .pause(1000)
        .click('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(5)');
    };
    this.registerNewUser = function () {
        browser
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(2)', 'Romain')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(3)', 'Couperier')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(4)', '260724748')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(5)', 'romain.couperier@mail.mcgill.ca')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(6)', 'password')
        .click('#register > div > div.half.right.aos-init.aos-animate > div > input.btn.btn-primary.py-2.px-4.text-white')
        .pause(1000);
        browser.assert.containsText('#register > div > div.half.right.aos-init.aos-animate > div > span', 'Success: Admin Created!');
    };
    this.registerErrorUser = function () {
        browser
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(2)', 'Romain')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(3)', 'Couperier')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(4)', '1')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(5)', 'romain.couperier@mail.mcgill.ca')
        .setValue('#register > div > div.half.right.aos-init.aos-animate > div > input:nth-child(6)', 'password')
        .click('#register > div > div.half.right.aos-init.aos-animate > div > input.btn.btn-primary.py-2.px-4.text-white')
        .pause(1000);
        browser.assert.containsText('#register > div > div.half.right.aos-init.aos-animate > div > span', 'Error: UserID is not a Valid Admin ID!');
    };
    this.clickLogin = function () {
        browser
        .waitForElementVisible('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(6)', 5000)
        .pause(1000)
        .click('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(6)');
    };
    this.signIn = function () {
        browser
        .setValue('#login > div > div.half.right.aos-init.aos-animate > div > div > input:nth-child(1)', 'b')
        .setValue('#login > div > div.half.right.aos-init.aos-animate > div > div > input:nth-child(2)', 'b')
        .click('#login > div > div.half.right.aos-init.aos-animate > div > div > input.btn.btn-primary.py-2.px-4.text-white')
        .pause(1000);
        browser.assert.containsText('#app > div > div.hello > div.site-section.site-hero > div > div > div > h1', 'Coop-erator Administrator');
    };
    this.allStudents = function () {
        browser
        .waitForElementVisible('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(2)', 5000)
        .pause(1000)
        .click('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(2)')
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'All Students');
    };
    this.clickStudent = function () {
        browser
        .waitForElementVisible('#table > table > tr:nth-child(2) > td:nth-child(1)', 5000)
        .pause(1000)
        browser.assert.containsText('#table > table > tr:nth-child(2) > td:nth-child(1) > a', "N'Golo");
        browser
        .click('#table > table > tr:nth-child(2) > td:nth-child(1) > a')
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'Student Coops');
    };
    this.clickCoop = function () {
        browser
        .waitForElementVisible('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(1)', 5000)
        .pause(1000)
        browser.assert.containsText('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(1)', '8');
        browser
        .click('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(1) > a')
        .pause(1000)
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'Coop Forms');
    };
    this.clickForm = function () {
        browser
        .waitForElementVisible('#table > table:nth-child(2) > tr:nth-child(2) > td:nth-child(1)', 5000)
        .pause(1000)
        browser.assert.containsText('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(1) > a', 'Tasks Workload Report');
        browser
        .click('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(1) > a')
        .pause(1000)
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'View & Edit Form');
    };
    this.editForm = function () {
        browser
        .waitForElementVisible('#id > div > div > div > div.half.right.aos-init.aos-animate > div > div:nth-child(3) > input:nth-child(1)', 5000)
        .setValue('#id > div > div > div > div.half.right.aos-init.aos-animate > div > div:nth-child(3) > input:nth-child(1)', '35')
        .pause(1000)
        .click('#id > div > div > div > div.half.right.aos-init.aos-animate > div > div:nth-child(4) > input')
        .pause(1000)
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#table > table > div:nth-child(2) > tr:nth-child(1) > td', '35');
    };
    this.allReminders = function () {
        browser
        .waitForElementVisible('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(3) > a', 5000)
        .pause(1000)
        .click('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(3) > a')
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'All Reminders');
    };
    this.sendReminder = function () {
        browser
        .setValue('#id > div > div > div > div:nth-child(2) > input:nth-child(1)', 'Meeting')
        .setValue('#id > div > div > div > div:nth-child(2) > input:nth-child(3)', '05/01/2019')
        .setValue('#id > div > div > div > div:nth-child(2) > input:nth-child(5)', '05/02/2019')
        .setValue('#id > div > div > div > div:nth-child(2) > input:nth-child(6)', 'You have meeting about your coop application')
        .setValue('#id > div > div > div > div:nth-child(2) > input:nth-child(7)', '1')
        .setValue('#id > div > div > div > div:nth-child(2) > input:nth-child(8)', '8')
        .click('#id > div > div > div > div:nth-child(2) > input.btn.btn-primary.py-2.px-4.text-white')
        .pause(1000);
    };
    this.clickStudentReminders = function () {
        browser
        .waitForElementVisible('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(7)', 5000)
        .pause(1000)
        browser.assert.containsText('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(7)', 'View Reminders');
        browser
        .click('#table > table:nth-child(2) > tr:nth-child(3) > td:nth-child(7) > a')
        .pause(1000)
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'Coop Reminders');
        browser.assert.containsText('#table > table:nth-child(2) > tr:nth-child(2) > td:nth-child(2)', 'Meeting');
    };
    this.allStats = function () {
        browser
        .waitForElementVisible('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(5)', 5000)
        .pause(1000)
        .click('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(5) > a')
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#stats > div > div > div > h1', 'Semester Stats');
    };
    this.inputStats = function () {
        browser
        .setValue('#stats > div > div > div > div:nth-child(2) > input.login-text', 'winter2019')
        .click('#stats > div > div > div > div:nth-child(2) > input.btn.btn-primary.py-2.px-4.text-white')
        .pause(1000)
        .waitForElementVisible('#table > table > tr:nth-child(2) > td:nth-child(1)',5000);
    };
    this.allEmployers = function () {
        browser
        .waitForElementVisible('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(4) > a', 5000)
        .pause(1000)
        .click('#app > div > header > div > div > div.col-12.col-md-10 > nav > ul > li:nth-child(4) > a')
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'All Employers');
    };
    this.clickEmployer = function () {
        browser
        .waitForElementVisible('#table > table > tr:nth-child(2) > td:nth-child(1) > a', 5000)
        .pause(1000)
        browser.assert.containsText('#table > table > tr:nth-child(2) > td:nth-child(1) > a', "Didier");
        browser
        .click('#table > table > tr:nth-child(2) > td:nth-child(1) > a')
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'Employer Coops');
    };
    this.clickEmployerCoop = function () {
        browser
        .waitForElementVisible('#table > table:nth-child(2) > tr:nth-child(2) > td:nth-child(1)', 5000)
        .pause(1000)
        .click('#table > table:nth-child(2) > tr:nth-child(2) > td:nth-child(1) > a')
        .pause(1000)
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'Coop Forms');
    };
    this.clickEmployerForm = function () {
        browser
        .waitForElementVisible('#table > table:nth-child(2) > tr:nth-child(2) > td:nth-child(1) > a', 5000)
        .pause(1000)
        .click('#table > table:nth-child(2) > tr:nth-child(2) > td:nth-child(1) > a')
        .pause(1000)
        .waitForElementVisible('body', 1000);
        browser.assert.containsText('#id > div > div > div > h1', 'View & Edit Form');
    };
    return this;
 };