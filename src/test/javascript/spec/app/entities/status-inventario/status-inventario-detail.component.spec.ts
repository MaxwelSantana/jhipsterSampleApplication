/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatusInventarioDetailComponent } from '../../../../../../main/webapp/app/entities/status-inventario/status-inventario-detail.component';
import { StatusInventarioService } from '../../../../../../main/webapp/app/entities/status-inventario/status-inventario.service';
import { StatusInventario } from '../../../../../../main/webapp/app/entities/status-inventario/status-inventario.model';

describe('Component Tests', () => {

    describe('StatusInventario Management Detail Component', () => {
        let comp: StatusInventarioDetailComponent;
        let fixture: ComponentFixture<StatusInventarioDetailComponent>;
        let service: StatusInventarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [StatusInventarioDetailComponent],
                providers: [
                    StatusInventarioService
                ]
            })
            .overrideTemplate(StatusInventarioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StatusInventarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusInventarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new StatusInventario(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.statusInventario).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
