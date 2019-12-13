import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NotifTemplateComponentsPage, NotifTemplateDeleteDialog, NotifTemplateUpdatePage } from './notif-template.page-object';

const expect = chai.expect;

describe('NotifTemplate e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let notifTemplateComponentsPage: NotifTemplateComponentsPage;
  let notifTemplateUpdatePage: NotifTemplateUpdatePage;
  let notifTemplateDeleteDialog: NotifTemplateDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NotifTemplates', async () => {
    await navBarPage.goToEntity('notif-template');
    notifTemplateComponentsPage = new NotifTemplateComponentsPage();
    await browser.wait(ec.visibilityOf(notifTemplateComponentsPage.title), 5000);
    expect(await notifTemplateComponentsPage.getTitle()).to.eq('Notif Templates');
  });

  it('should load create NotifTemplate page', async () => {
    await notifTemplateComponentsPage.clickOnCreateButton();
    notifTemplateUpdatePage = new NotifTemplateUpdatePage();
    expect(await notifTemplateUpdatePage.getPageTitle()).to.eq('Create or edit a Notif Template');
    await notifTemplateUpdatePage.cancel();
  });

  it('should create and save NotifTemplates', async () => {
    const nbButtonsBeforeCreate = await notifTemplateComponentsPage.countDeleteButtons();

    await notifTemplateComponentsPage.clickOnCreateButton();
    await promise.all([
      notifTemplateUpdatePage.setEmailAddressesInput('emailAddresses'),
      notifTemplateUpdatePage.setMessageInput('message')
    ]);
    expect(await notifTemplateUpdatePage.getEmailAddressesInput()).to.eq(
      'emailAddresses',
      'Expected EmailAddresses value to be equals to emailAddresses'
    );
    expect(await notifTemplateUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    await notifTemplateUpdatePage.save();
    expect(await notifTemplateUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await notifTemplateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NotifTemplate', async () => {
    const nbButtonsBeforeDelete = await notifTemplateComponentsPage.countDeleteButtons();
    await notifTemplateComponentsPage.clickOnLastDeleteButton();

    notifTemplateDeleteDialog = new NotifTemplateDeleteDialog();
    expect(await notifTemplateDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Notif Template?');
    await notifTemplateDeleteDialog.clickOnConfirmButton();

    expect(await notifTemplateComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
