/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TipoConfiguracaoDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao-delete-dialog.component';
import { TipoConfiguracaoService } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao.service';

describe('Component Tests', () => {

    describe('TipoConfiguracao Management Delete Component', () => {
        let comp: TipoConfiguracaoDeleteDialogComponent;
        let fixture: ComponentFixture<TipoConfiguracaoDeleteDialogComponent>;
        let service: TipoConfiguracaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TipoConfiguracaoDeleteDialogComponent],
                providers: [
                    TipoConfiguracaoService
                ]
            })
            .overrideTemplate(TipoConfiguracaoDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TipoConfiguracaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoConfiguracaoService);
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
