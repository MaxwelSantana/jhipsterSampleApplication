/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogAlteracaoContagemDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem-delete-dialog.component';
import { LogAlteracaoContagemService } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.service';

describe('Component Tests', () => {

    describe('LogAlteracaoContagem Management Delete Component', () => {
        let comp: LogAlteracaoContagemDeleteDialogComponent;
        let fixture: ComponentFixture<LogAlteracaoContagemDeleteDialogComponent>;
        let service: LogAlteracaoContagemService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogAlteracaoContagemDeleteDialogComponent],
                providers: [
                    LogAlteracaoContagemService
                ]
            })
            .overrideTemplate(LogAlteracaoContagemDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogAlteracaoContagemDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogAlteracaoContagemService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
