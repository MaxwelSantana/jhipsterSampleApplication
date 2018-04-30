/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelColetaDialogComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta-dialog.component';
import { CampoCustomizavelColetaService } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.service';
import { CampoCustomizavelColeta } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.model';
import { ClienteService } from '../../../../../../main/webapp/app/entities/cliente';

describe('Component Tests', () => {

    describe('CampoCustomizavelColeta Management Dialog Component', () => {
        let comp: CampoCustomizavelColetaDialogComponent;
        let fixture: ComponentFixture<CampoCustomizavelColetaDialogComponent>;
        let service: CampoCustomizavelColetaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelColetaDialogComponent],
                providers: [
                    ClienteService,
                    CampoCustomizavelColetaService
                ]
            })
            .overrideTemplate(CampoCustomizavelColetaDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelColetaDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelColetaService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CampoCustomizavelColeta(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.campoCustomizavelColeta = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'campoCustomizavelColetaListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CampoCustomizavelColeta();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.campoCustomizavelColeta = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'campoCustomizavelColetaListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
