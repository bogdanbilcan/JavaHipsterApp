import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { INotifTemplate, NotifTemplate } from 'app/shared/model/notif-template.model';
import { NotifTemplateService } from './notif-template.service';

@Component({
  selector: 'jhi-notif-template-update',
  templateUrl: './notif-template-update.component.html'
})
export class NotifTemplateUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    emailAddresses: [null, [Validators.required]],
    message: [null, [Validators.maxLength(600)]]
  });

  constructor(protected notifTemplateService: NotifTemplateService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ notifTemplate }) => {
      this.updateForm(notifTemplate);
    });
  }

  updateForm(notifTemplate: INotifTemplate) {
    this.editForm.patchValue({
      id: notifTemplate.id,
      emailAddresses: notifTemplate.emailAddresses,
      message: notifTemplate.message
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const notifTemplate = this.createFromForm();
    if (notifTemplate.id !== undefined) {
      this.subscribeToSaveResponse(this.notifTemplateService.update(notifTemplate));
    } else {
      this.subscribeToSaveResponse(this.notifTemplateService.create(notifTemplate));
    }
  }

  private createFromForm(): INotifTemplate {
    return {
      ...new NotifTemplate(),
      id: this.editForm.get(['id']).value,
      emailAddresses: this.editForm.get(['emailAddresses']).value,
      message: this.editForm.get(['message']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotifTemplate>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
