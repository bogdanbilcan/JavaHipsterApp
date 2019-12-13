import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PortofoliuComponentsPage } from './portofoliu.page-object';

const expect = chai.expect;

describe('Portofoliu e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let portofoliuComponentsPage: PortofoliuComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Portofolius', async () => {
    await navBarPage.goToEntity('portofoliu');
    portofoliuComponentsPage = new PortofoliuComponentsPage();
    await browser.wait(ec.visibilityOf(portofoliuComponentsPage.title), 5000);
    expect(await portofoliuComponentsPage.getTitle()).to.eq('Portofolius');
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
