/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelCadastroDetailComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro-detail.component';
import { CampoCustomizavelCadastroService } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.service';
import { CampoCustomizavelCadastro } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.model';

describe('Component Tests', () => {

    describe('CampoCustomizavelCadastro Management Detail Component', () => {
        let comp: CampoCustomizavelCadastroDetailComponent;
        let fixture: ComponentFixture<CampoCustomizavelCadastroDetailComponent>;
        let service: CampoCustomizavelCadastroService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelCadastroDetailComponent],
                providers: [
                    CampoCustomizavelCadastroService
                ]
            })
            .overrideTemplate(CampoCustomizavelCadastroDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelCadastroDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelCadastroService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CampoCustomizavelCadastro(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.campoCustomizavelCadastro).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
