/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ConfiguracaoColetorDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor-delete-dialog.component';
import { ConfiguracaoColetorService } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.service';

describe('Component Tests', () => {

    describe('ConfiguracaoColetor Management Delete Component', () => {
        let comp: ConfiguracaoColetorDeleteDialogComponent;
        let fixture: ComponentFixture<ConfiguracaoColetorDeleteDialogComponent>;
        let service: ConfiguracaoColetorService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ConfiguracaoColetorDeleteDialogComponent],
                providers: [
                    ConfiguracaoColetorService
                ]
            })
            .overrideTemplate(ConfiguracaoColetorDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracaoColetorDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracaoColetorService);
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
