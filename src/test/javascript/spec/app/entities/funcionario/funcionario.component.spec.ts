/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FuncionarioComponent } from '../../../../../../main/webapp/app/entities/funcionario/funcionario.component';
import { FuncionarioService } from '../../../../../../main/webapp/app/entities/funcionario/funcionario.service';
import { Funcionario } from '../../../../../../main/webapp/app/entities/funcionario/funcionario.model';

describe('Component Tests', () => {

    describe('Funcionario Management Component', () => {
        let comp: FuncionarioComponent;
        let fixture: ComponentFixture<FuncionarioComponent>;
        let service: FuncionarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [FuncionarioComponent],
                providers: [
                    FuncionarioService
                ]
            })
            .overrideTemplate(FuncionarioComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FuncionarioComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FuncionarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Funcionario(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.funcionarios[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
