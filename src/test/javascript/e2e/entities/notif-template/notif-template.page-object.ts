import { element, by, ElementFinder } from 'protractor';

export class NotifTemplateComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-notif-template div table .btn-danger'));
  title = element.all(by.css('jhi-notif-template div h2#page-heading span')).first();

  async clickOnCreateButton() {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton() {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getText();
  }
}

export class NotifTemplateUpdatePage {
  pageTitle = element(by.id('jhi-notif-template-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  emailAddressesInput = element(by.id('field_emailAddresses'));
  messageInput = element(by.id('field_message'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setEmailAddressesInput(emailAddresses) {
    await this.emailAddressesInput.sendKeys(emailAddresses);
  }

  async getEmailAddressesInput() {
    return await this.emailAddressesInput.getAttribute('value');
  }

  async setMessageInput(message) {
    await this.messageInput.sendKeys(message);
  }

  async getMessageInput() {
    return await this.messageInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class NotifTemplateDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-notifTemplate-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-notifTemplate'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
