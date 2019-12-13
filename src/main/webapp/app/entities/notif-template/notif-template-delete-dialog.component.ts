import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotifTemplate } from 'app/shared/model/notif-template.model';
import { NotifTemplateService } from './notif-template.service';

@Component({
  templateUrl: './notif-template-delete-dialog.component.html'
})
export class NotifTemplateDeleteDialogComponent {
  notifTemplate: INotifTemplate;

  constructor(
    protected notifTemplateService: NotifTemplateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.notifTemplateService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'notifTemplateListModification',
        content: 'Deleted an notifTemplate'
      });
      this.activeModal.dismiss(true);
    });
  }
}
