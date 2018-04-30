/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelColetaDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta-delete-dialog.component';
import { CampoCustomizavelColetaService } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.service';

describe('Component Tests', () => {

    describe('CampoCustomizavelColeta Management Delete Component', () => {
        let comp: CampoCustomizavelColetaDeleteDialogComponent;
        let fixture: ComponentFixture<CampoCustomizavelColetaDeleteDialogComponent>;
        let service: CampoCustomizavelColetaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelColetaDeleteDialogComponent],
                providers: [
                    CampoCustomizavelColetaService
                ]
            })
            .overrideTemplate(CampoCustomizavelColetaDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelColetaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelColetaService);
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
