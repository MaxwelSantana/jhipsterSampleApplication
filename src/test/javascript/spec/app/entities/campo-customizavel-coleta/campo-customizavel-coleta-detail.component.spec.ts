/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelColetaDetailComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta-detail.component';
import { CampoCustomizavelColetaService } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.service';
import { CampoCustomizavelColeta } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.model';

describe('Component Tests', () => {

    describe('CampoCustomizavelColeta Management Detail Component', () => {
        let comp: CampoCustomizavelColetaDetailComponent;
        let fixture: ComponentFixture<CampoCustomizavelColetaDetailComponent>;
        let service: CampoCustomizavelColetaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelColetaDetailComponent],
                providers: [
                    CampoCustomizavelColetaService
                ]
            })
            .overrideTemplate(CampoCustomizavelColetaDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelColetaDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelColetaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CampoCustomizavelColeta(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.campoCustomizavelColeta).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
