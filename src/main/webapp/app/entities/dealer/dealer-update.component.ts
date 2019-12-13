import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDealer, Dealer } from 'app/shared/model/dealer.model';
import { DealerService } from './dealer.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IStoc } from 'app/shared/model/stoc.model';
import { StocService } from 'app/entities/stoc/stoc.service';
import { IPortofoliu } from 'app/shared/model/portofoliu.model';
import { PortofoliuService } from 'app/entities/portofoliu/portofoliu.service';

@Component({
  selector: 'jhi-dealer-update',
  templateUrl: './dealer-update.component.html'
})
export class DealerUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  stocs: IStoc[];

  portofolius: IPortofoliu[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [],
    tipAutovehicule: [],
    dealerId: [null, [Validators.required]],
    users: [],
    stocId: [],
    portofoliuId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected dealerService: DealerService,
    protected userService: UserService,
    protected stocService: StocService,
    protected portofoliuService: PortofoliuService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dealer }) => {
      this.updateForm(dealer);
    });
    this.userService
      .query()
      .subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.stocService
      .query()
      .subscribe((res: HttpResponse<IStoc[]>) => (this.stocs = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.portofoliuService
      .query()
      .subscribe(
        (res: HttpResponse<IPortofoliu[]>) => (this.portofolius = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(dealer: IDealer) {
    this.editForm.patchValue({
      id: dealer.id,
      name: dealer.name,
      description: dealer.description,
      tipAutovehicule: dealer.tipAutovehicule,
      dealerId: dealer.dealerId,
      users: dealer.users,
      stocId: dealer.stocId,
      portofoliuId: dealer.portofoliuId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dealer = this.createFromForm();
    if (dealer.id !== undefined) {
      this.subscribeToSaveResponse(this.dealerService.update(dealer));
    } else {
      this.subscribeToSaveResponse(this.dealerService.create(dealer));
    }
  }

  private createFromForm(): IDealer {
    return {
      ...new Dealer(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      description: this.editForm.get(['description']).value,
      tipAutovehicule: this.editForm.get(['tipAutovehicule']).value,
      dealerId: this.editForm.get(['dealerId']).value,
      users: this.editForm.get(['users']).value,
      stocId: this.editForm.get(['stocId']).value,
      portofoliuId: this.editForm.get(['portofoliuId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDealer>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackStocById(index: number, item: IStoc) {
    return item.id;
  }

  trackPortofoliuById(index: number, item: IPortofoliu) {
    return item.id;
  }

  getSelected(selectedVals: any[], option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
