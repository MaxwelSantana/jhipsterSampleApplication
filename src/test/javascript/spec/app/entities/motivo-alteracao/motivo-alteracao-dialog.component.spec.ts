/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MotivoAlteracaoDialogComponent } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao-dialog.component';
import { MotivoAlteracaoService } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.service';
import { MotivoAlteracao } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.model';

describe('Component Tests', () => {

    describe('MotivoAlteracao Management Dialog Component', () => {
        let comp: MotivoAlteracaoDialogComponent;
        let fixture: ComponentFixture<MotivoAlteracaoDialogComponent>;
        let service: MotivoAlteracaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [MotivoAlteracaoDialogComponent],
                providers: [
                    MotivoAlteracaoService
                ]
            })
            .overrideTemplate(MotivoAlteracaoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MotivoAlteracaoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotivoAlteracaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MotivoAlteracao(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.motivoAlteracao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'motivoAlteracaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MotivoAlteracao();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.motivoAlteracao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'motivoAlteracaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
