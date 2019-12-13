import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { StocComponentsPage } from './stoc.page-object';

const expect = chai.expect;

describe('Stoc e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let stocComponentsPage: StocComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Stocs', async () => {
    await navBarPage.goToEntity('stoc');
    stocComponentsPage = new StocComponentsPage();
    await browser.wait(ec.visibilityOf(stocComponentsPage.title), 5000);
    expect(await stocComponentsPage.getTitle()).to.eq('Stocs');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
