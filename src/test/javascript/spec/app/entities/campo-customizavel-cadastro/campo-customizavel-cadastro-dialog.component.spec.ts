/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelCadastroDialogComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro-dialog.component';
import { CampoCustomizavelCadastroService } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.service';
import { CampoCustomizavelCadastro } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.model';
import { ClienteService } from '../../../../../../main/webapp/app/entities/cliente';

describe('Component Tests', () => {

    describe('CampoCustomizavelCadastro Management Dialog Component', () => {
        let comp: CampoCustomizavelCadastroDialogComponent;
        let fixture: ComponentFixture<CampoCustomizavelCadastroDialogComponent>;
        let service: CampoCustomizavelCadastroService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelCadastroDialogComponent],
                providers: [
                    ClienteService,
                    CampoCustomizavelCadastroService
                ]
            })
            .overrideTemplate(CampoCustomizavelCadastroDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelCadastroDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelCadastroService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CampoCustomizavelCadastro(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.campoCustomizavelCadastro = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'campoCustomizavelCadastroListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CampoCustomizavelCadastro();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.campoCustomizavelCadastro = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'campoCustomizavelCadastroListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
