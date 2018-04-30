/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelCadastroComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.component';
import { CampoCustomizavelCadastroService } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.service';
import { CampoCustomizavelCadastro } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.model';

describe('Component Tests', () => {

    describe('CampoCustomizavelCadastro Management Component', () => {
        let comp: CampoCustomizavelCadastroComponent;
        let fixture: ComponentFixture<CampoCustomizavelCadastroComponent>;
        let service: CampoCustomizavelCadastroService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelCadastroComponent],
                providers: [
                    CampoCustomizavelCadastroService
                ]
            })
            .overrideTemplate(CampoCustomizavelCadastroComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelCadastroComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelCadastroService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CampoCustomizavelCadastro(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.campoCustomizavelCadastros[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
