/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ContagemDialogComponent } from '../../../../../../main/webapp/app/entities/contagem/contagem-dialog.component';
import { ContagemService } from '../../../../../../main/webapp/app/entities/contagem/contagem.service';
import { Contagem } from '../../../../../../main/webapp/app/entities/contagem/contagem.model';
import { SecaoService } from '../../../../../../main/webapp/app/entities/secao';
import { StatusContagemService } from '../../../../../../main/webapp/app/entities/status-contagem';
import { MotivoAlteracaoService } from '../../../../../../main/webapp/app/entities/motivo-alteracao';

describe('Component Tests', () => {

    describe('Contagem Management Dialog Component', () => {
        let comp: ContagemDialogComponent;
        let fixture: ComponentFixture<ContagemDialogComponent>;
        let service: ContagemService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ContagemDialogComponent],
                providers: [
                    SecaoService,
                    StatusContagemService,
                    MotivoAlteracaoService,
                    ContagemService
                ]
            })
            .overrideTemplate(ContagemDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContagemDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContagemService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Contagem(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.contagem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'contagemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Contagem();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.contagem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'contagemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
