/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { InventarioDetailComponent } from '../../../../../../main/webapp/app/entities/inventario/inventario-detail.component';
import { InventarioService } from '../../../../../../main/webapp/app/entities/inventario/inventario.service';
import { Inventario } from '../../../../../../main/webapp/app/entities/inventario/inventario.model';

describe('Component Tests', () => {

    describe('Inventario Management Detail Component', () => {
        let comp: InventarioDetailComponent;
        let fixture: ComponentFixture<InventarioDetailComponent>;
        let service: InventarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [InventarioDetailComponent],
                providers: [
                    InventarioService
                ]
            })
            .overrideTemplate(InventarioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InventarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InventarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Inventario(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.inventario).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
