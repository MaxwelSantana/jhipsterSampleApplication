/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogStatusInventarioDialogComponent } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario-dialog.component';
import { LogStatusInventarioService } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.service';
import { LogStatusInventario } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.model';
import { StatusInventarioService } from '../../../../../../main/webapp/app/entities/status-inventario';
import { InventarioService } from '../../../../../../main/webapp/app/entities/inventario';

describe('Component Tests', () => {

    describe('LogStatusInventario Management Dialog Component', () => {
        let comp: LogStatusInventarioDialogComponent;
        let fixture: ComponentFixture<LogStatusInventarioDialogComponent>;
        let service: LogStatusInventarioService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogStatusInventarioDialogComponent],
                providers: [
                    StatusInventarioService,
                    InventarioService,
                    LogStatusInventarioService
                ]
            })
            .overrideTemplate(LogStatusInventarioDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogStatusInventarioDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogStatusInventarioService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LogStatusInventario(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.logStatusInventario = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'logStatusInventarioListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new LogStatusInventario();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.logStatusInventario = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'logStatusInventarioListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
