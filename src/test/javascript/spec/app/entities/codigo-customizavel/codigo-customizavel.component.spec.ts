/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CodigoCustomizavelComponent } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.component';
import { CodigoCustomizavelService } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.service';
import { CodigoCustomizavel } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.model';

describe('Component Tests', () => {

    describe('CodigoCustomizavel Management Component', () => {
        let comp: CodigoCustomizavelComponent;
        let fixture: ComponentFixture<CodigoCustomizavelComponent>;
        let service: CodigoCustomizavelService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CodigoCustomizavelComponent],
                providers: [
                    CodigoCustomizavelService
                ]
            })
            .overrideTemplate(CodigoCustomizavelComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CodigoCustomizavelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CodigoCustomizavelService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CodigoCustomizavel(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.codigoCustomizavels[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
