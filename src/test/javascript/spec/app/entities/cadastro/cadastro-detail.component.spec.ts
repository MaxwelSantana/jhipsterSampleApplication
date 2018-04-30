/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CadastroDetailComponent } from '../../../../../../main/webapp/app/entities/cadastro/cadastro-detail.component';
import { CadastroService } from '../../../../../../main/webapp/app/entities/cadastro/cadastro.service';
import { Cadastro } from '../../../../../../main/webapp/app/entities/cadastro/cadastro.model';

describe('Component Tests', () => {

    describe('Cadastro Management Detail Component', () => {
        let comp: CadastroDetailComponent;
        let fixture: ComponentFixture<CadastroDetailComponent>;
        let service: CadastroService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CadastroDetailComponent],
                providers: [
                    CadastroService
                ]
            })
            .overrideTemplate(CadastroDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CadastroDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CadastroService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Cadastro(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.cadastro).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
