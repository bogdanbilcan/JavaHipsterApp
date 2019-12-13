import { element, by, ElementFinder } from 'protractor';

export class DealerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-dealer div table .btn-danger'));
  title = element.all(by.css('jhi-dealer div h2#page-heading span')).first();

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

export class DealerUpdatePage {
  pageTitle = element(by.id('jhi-dealer-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  nameInput = element(by.id('field_name'));
  descriptionInput = element(by.id('field_description'));
  tipAutovehiculeSelect = element(by.id('field_tipAutovehicule'));
  dealerIdInput = element(by.id('field_dealerId'));
  userSelect = element(by.id('field_user'));
  stocSelect = element(by.id('field_stoc'));
  portofoliuSelect = element(by.id('field_portofoliu'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return await this.nameInput.getAttribute('value');
  }

  async setDescriptionInput(description) {
    await this.descriptionInput.sendKeys(description);
  }

  async getDescriptionInput() {
    return await this.descriptionInput.getAttribute('value');
  }

  async setTipAutovehiculeSelect(tipAutovehicule) {
    await this.tipAutovehiculeSelect.sendKeys(tipAutovehicule);
  }

  async getTipAutovehiculeSelect() {
    return await this.tipAutovehiculeSelect.element(by.css('option:checked')).getText();
  }

  async tipAutovehiculeSelectLastOption() {
    await this.tipAutovehiculeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setDealerIdInput(dealerId) {
    await this.dealerIdInput.sendKeys(dealerId);
  }

  async getDealerIdInput() {
    return await this.dealerIdInput.getAttribute('value');
  }

  async userSelectLastOption() {
    await this.userSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async userSelectOption(option) {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect(): ElementFinder {
    return this.userSelect;
  }

  async getUserSelectedOption() {
    return await this.userSelect.element(by.css('option:checked')).getText();
  }

  async stocSelectLastOption() {
    await this.stocSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async stocSelectOption(option) {
    await this.stocSelect.sendKeys(option);
  }

  getStocSelect(): ElementFinder {
    return this.stocSelect;
  }

  async getStocSelectedOption() {
    return await this.stocSelect.element(by.css('option:checked')).getText();
  }

  async portofoliuSelectLastOption() {
    await this.portofoliuSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async portofoliuSelectOption(option) {
    await this.portofoliuSelect.sendKeys(option);
  }

  getPortofoliuSelect(): ElementFinder {
    return this.portofoliuSelect;
  }

  async getPortofoliuSelectedOption() {
    return await this.portofoliuSelect.element(by.css('option:checked')).getText();
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

export class DealerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dealer-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dealer'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
