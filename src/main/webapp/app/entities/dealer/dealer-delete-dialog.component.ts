import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDealer } from 'app/shared/model/dealer.model';
import { DealerService } from './dealer.service';

@Component({
  templateUrl: './dealer-delete-dialog.component.html'
})
export class DealerDeleteDialogComponent {
  dealer: IDealer;

  constructor(protected dealerService: DealerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dealerService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dealerListModification',
        content: 'Deleted an dealer'
      });
      this.activeModal.dismiss(true);
    });
  }
}
