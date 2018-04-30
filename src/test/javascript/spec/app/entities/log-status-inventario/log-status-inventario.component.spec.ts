/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogStatusInventarioComponent } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.component';
import { LogStatusInventarioService } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.service';
import { LogStatusInventario } from '../../../../../../main/webapp/app/entities/log-status-inventario/log-status-inventario.model';

describe('Component Tests', () => {

    describe('LogStatusInventario Management Component', () => {
        let comp: LogStatusInventarioComponent;
        let fixture: ComponentFixture<LogStatusInventarioComponent>;
        let service: LogStatusInventarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogStatusInventarioComponent],
                providers: [
                    LogStatusInventarioService
                ]
            })
            .overrideTemplate(LogStatusInventarioComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogStatusInventarioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogStatusInventarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new LogStatusInventario(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.logStatusInventarios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
