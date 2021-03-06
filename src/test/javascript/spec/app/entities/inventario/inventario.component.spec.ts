/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { InventarioComponent } from '../../../../../../main/webapp/app/entities/inventario/inventario.component';
import { InventarioService } from '../../../../../../main/webapp/app/entities/inventario/inventario.service';
import { Inventario } from '../../../../../../main/webapp/app/entities/inventario/inventario.model';

describe('Component Tests', () => {

    describe('Inventario Management Component', () => {
        let comp: InventarioComponent;
        let fixture: ComponentFixture<InventarioComponent>;
        let service: InventarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [InventarioComponent],
                providers: [
                    InventarioService
                ]
            })
            .overrideTemplate(InventarioComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InventarioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InventarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Inventario(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.inventarios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
