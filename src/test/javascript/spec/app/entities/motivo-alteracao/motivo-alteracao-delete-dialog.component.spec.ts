/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MotivoAlteracaoDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao-delete-dialog.component';
import { MotivoAlteracaoService } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.service';

describe('Component Tests', () => {

    describe('MotivoAlteracao Management Delete Component', () => {
        let comp: MotivoAlteracaoDeleteDialogComponent;
        let fixture: ComponentFixture<MotivoAlteracaoDeleteDialogComponent>;
        let service: MotivoAlteracaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [MotivoAlteracaoDeleteDialogComponent],
                providers: [
                    MotivoAlteracaoService
                ]
            })
            .overrideTemplate(MotivoAlteracaoDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MotivoAlteracaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotivoAlteracaoService);
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
