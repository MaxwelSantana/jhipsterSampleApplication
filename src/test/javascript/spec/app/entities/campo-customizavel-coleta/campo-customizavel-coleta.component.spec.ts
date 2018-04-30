/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelColetaComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.component';
import { CampoCustomizavelColetaService } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.service';
import { CampoCustomizavelColeta } from '../../../../../../main/webapp/app/entities/campo-customizavel-coleta/campo-customizavel-coleta.model';

describe('Component Tests', () => {

    describe('CampoCustomizavelColeta Management Component', () => {
        let comp: CampoCustomizavelColetaComponent;
        let fixture: ComponentFixture<CampoCustomizavelColetaComponent>;
        let service: CampoCustomizavelColetaService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelColetaComponent],
                providers: [
                    CampoCustomizavelColetaService
                ]
            })
            .overrideTemplate(CampoCustomizavelColetaComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelColetaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelColetaService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CampoCustomizavelColeta(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.campoCustomizavelColetas[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
