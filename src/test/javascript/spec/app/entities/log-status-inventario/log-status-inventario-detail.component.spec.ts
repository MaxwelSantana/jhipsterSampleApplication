/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogStatusInventarioDetailComponent } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario-detail.component';
import { LogStatusInventarioService } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.service';
import { LogStatusInventario } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.model';

describe('Component Tests', () => {

    describe('LogStatusInventario Management Detail Component', () => {
        let comp: LogStatusInventarioDetailComponent;
        let fixture: ComponentFixture<LogStatusInventarioDetailComponent>;
        let service: LogStatusInventarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogStatusInventarioDetailComponent],
                providers: [
                    LogStatusInventarioService
                ]
            })
            .overrideTemplate(LogStatusInventarioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogStatusInventarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogStatusInventarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new LogStatusInventario(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.logStatusInventario).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
