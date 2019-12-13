import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DealerComponentsPage, DealerDeleteDialog, DealerUpdatePage } from './dealer.page-object';

const expect = chai.expect;

describe('Dealer e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dealerComponentsPage: DealerComponentsPage;
  let dealerUpdatePage: DealerUpdatePage;
  let dealerDeleteDialog: DealerDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Dealers', async () => {
    await navBarPage.goToEntity('dealer');
    dealerComponentsPage = new DealerComponentsPage();
    await browser.wait(ec.visibilityOf(dealerComponentsPage.title), 5000);
    expect(await dealerComponentsPage.getTitle()).to.eq('Dealers');
  });

  it('should load create Dealer page', async () => {
    await dealerComponentsPage.clickOnCreateButton();
    dealerUpdatePage = new DealerUpdatePage();
    expect(await dealerUpdatePage.getPageTitle()).to.eq('Create or edit a Dealer');
    await dealerUpdatePage.cancel();
  });

  it('should create and save Dealers', async () => {
    const nbButtonsBeforeCreate = await dealerComponentsPage.countDeleteButtons();

    await dealerComponentsPage.clickOnCreateButton();
    await promise.all([
      dealerUpdatePage.setNameInput('name'),
      dealerUpdatePage.setDescriptionInput('description'),
      dealerUpdatePage.tipAutovehiculeSelectLastOption(),
      dealerUpdatePage.setDealerIdInput('dealerId'),
      // dealerUpdatePage.userSelectLastOption(),
      dealerUpdatePage.stocSelectLastOption(),
      dealerUpdatePage.portofoliuSelectLastOption()
    ]);
    expect(await dealerUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await dealerUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await dealerUpdatePage.getDealerIdInput()).to.eq('dealerId', 'Expected DealerId value to be equals to dealerId');
    await dealerUpdatePage.save();
    expect(await dealerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await dealerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Dealer', async () => {
    const nbButtonsBeforeDelete = await dealerComponentsPage.countDeleteButtons();
    await dealerComponentsPage.clickOnLastDeleteButton();

    dealerDeleteDialog = new DealerDeleteDialog();
    expect(await dealerDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Dealer?');
    await dealerDeleteDialog.clickOnConfirmButton();

    expect(await dealerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
