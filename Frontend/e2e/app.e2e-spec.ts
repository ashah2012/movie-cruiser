import { AppPage } from './app.po';
import { browser, by, element } from 'protractor';
import { protractor } from 'protractor/built/ptor';

describe('Frontend App', () => {
  let page: AppPage;
  beforeEach(() => {
    page = new AppPage();
  });
  it('should display Title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiserFrontend');
  });
  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login')
  });
  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-button')).click()
    expect(browser.getCurrentUrl()).toContain('/register')
  });
  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('Super User');
    browser.element(by.id('lastName')).sendKeys('Super lastName');
    browser.element(by.id('userId')).sendKeys('Super User34');
    browser.element(by.id('password')).sendKeys('Super UserPass');
    browser.element(by.css('.register-user')).click()
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/login')
  });
  it('should be able to login user and navigate to popular movies',() => {
    browser.element(by.id('userId')).sendKeys('Super User34');
    browser.element(by.id('password')).sendKeys('Super UserPass');
    browser.element(by.css('.login-user')).click()
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/movies/popular')
  });
  it('should be able to search for movies', () => {
    browser.element(by.css('.search-button')).click()
    browser.driver.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/movies/search')
    browser.driver.sleep(2000);
      browser.element(by.id('search-button-input')).sendKeys('Super')
      browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER)
      const searchItems = element.all(by.css('.movieTitle'));
      expect(searchItems.count()).toBe(20);
      for(let i = 0; i < 1; i++){
        expect(searchItems.get(i).getText()).toContain('Super');
      }
  });
  it('should be able to add movie to watchList', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.movie-thumbnail'));
    expect(searchItems.count()).toBe(20);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click()
  });
});