/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CodigoCustomizavelDetailComponent } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel-detail.component';
import { CodigoCustomizavelService } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.service';
import { CodigoCustomizavel } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.model';

describe('Component Tests', () => {

    describe('CodigoCustomizavel Management Detail Component', () => {
        let comp: CodigoCustomizavelDetailComponent;
        let fixture: ComponentFixture<CodigoCustomizavelDetailComponent>;
        let service: CodigoCustomizavelService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CodigoCustomizavelDetailComponent],
                providers: [
                    CodigoCustomizavelService
                ]
            })
            .overrideTemplate(CodigoCustomizavelDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CodigoCustomizavelDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CodigoCustomizavelService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CodigoCustomizavel(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.codigoCustomizavel).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
