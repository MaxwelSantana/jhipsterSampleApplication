/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CadastroComponent } from '../../../../../../main/webapp/app/entities/cadastro/cadastro.component';
import { CadastroService } from '../../../../../../main/webapp/app/entities/cadastro/cadastro.service';
import { Cadastro } from '../../../../../../main/webapp/app/entities/cadastro/cadastro.model';

describe('Component Tests', () => {

    describe('Cadastro Management Component', () => {
        let comp: CadastroComponent;
        let fixture: ComponentFixture<CadastroComponent>;
        let service: CadastroService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CadastroComponent],
                providers: [
                    CadastroService
                ]
            })
            .overrideTemplate(CadastroComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CadastroComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CadastroService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Cadastro(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.cadastros[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
