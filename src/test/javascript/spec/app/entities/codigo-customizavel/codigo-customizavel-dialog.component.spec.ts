/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CodigoCustomizavelDialogComponent } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel-dialog.component';
import { CodigoCustomizavelService } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.service';
import { CodigoCustomizavel } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.model';

describe('Component Tests', () => {

    describe('CodigoCustomizavel Management Dialog Component', () => {
        let comp: CodigoCustomizavelDialogComponent;
        let fixture: ComponentFixture<CodigoCustomizavelDialogComponent>;
        let service: CodigoCustomizavelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CodigoCustomizavelDialogComponent],
                providers: [
                    CodigoCustomizavelService
                ]
            })
            .overrideTemplate(CodigoCustomizavelDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CodigoCustomizavelDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CodigoCustomizavelService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CodigoCustomizavel(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.codigoCustomizavel = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'codigoCustomizavelListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CodigoCustomizavel();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.codigoCustomizavel = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'codigoCustomizavelListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
