/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatusInventarioComponent } from '../../../../../../main/webapp/app/entities/status-inventario/status-inventario.component';
import { StatusInventarioService } from '../../../../../../main/webapp/app/entities/status-inventario/status-inventario.service';
import { StatusInventario } from '../../../../../../main/webapp/app/entities/status-inventario/status-inventario.model';

describe('Component Tests', () => {

    describe('StatusInventario Management Component', () => {
        let comp: StatusInventarioComponent;
        let fixture: ComponentFixture<StatusInventarioComponent>;
        let service: StatusInventarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [StatusInventarioComponent],
                providers: [
                    StatusInventarioService
                ]
            })
            .overrideTemplate(StatusInventarioComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StatusInventarioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusInventarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new StatusInventario(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.statusInventarios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
