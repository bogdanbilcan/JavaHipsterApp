import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JavaHipsterAppTestModule } from '../../../test.module';
import { NotifTemplateUpdateComponent } from 'app/entities/notif-template/notif-template-update.component';
import { NotifTemplateService } from 'app/entities/notif-template/notif-template.service';
import { NotifTemplate } from 'app/shared/model/notif-template.model';

describe('Component Tests', () => {
  describe('NotifTemplate Management Update Component', () => {
    let comp: NotifTemplateUpdateComponent;
    let fixture: ComponentFixture<NotifTemplateUpdateComponent>;
    let service: NotifTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JavaHipsterAppTestModule],
        declarations: [NotifTemplateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NotifTemplateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotifTemplateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotifTemplateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotifTemplate(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotifTemplate();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
