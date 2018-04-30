/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogAlteracaoContagemDialogComponent } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem-dialog.component';
import { LogAlteracaoContagemService } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.service';
import { LogAlteracaoContagem } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.model';
import { ContagemService } from '../../../../../../main/webapp/app/entities/contagem';

describe('Component Tests', () => {

    describe('LogAlteracaoContagem Management Dialog Component', () => {
        let comp: LogAlteracaoContagemDialogComponent;
        let fixture: ComponentFixture<LogAlteracaoContagemDialogComponent>;
        let service: LogAlteracaoContagemService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogAlteracaoContagemDialogComponent],
                providers: [
                    ContagemService,
                    LogAlteracaoContagemService
                ]
            })
            .overrideTemplate(LogAlteracaoContagemDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogAlteracaoContagemDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogAlteracaoContagemService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LogAlteracaoContagem(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.logAlteracaoContagem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'logAlteracaoContagemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LogAlteracaoContagem();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.logAlteracaoContagem = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'logAlteracaoContagemListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
