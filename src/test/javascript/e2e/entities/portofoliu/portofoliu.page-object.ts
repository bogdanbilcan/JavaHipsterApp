import { element, by } from 'protractor';

export class PortofoliuComponentsPage {
  title = element.all(by.css('jhi-portofoliu div h2#page-heading span')).first();

  async getTitle() {
    return this.title.getText();
  }
}
