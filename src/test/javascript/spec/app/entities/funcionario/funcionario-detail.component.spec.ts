/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FuncionarioDetailComponent } from '../../../../../../main/webapp/app/entities/funcionario/funcionario-detail.component';
import { FuncionarioService } from '../../../../../../main/webapp/app/entities/funcionario/funcionario.service';
import { Funcionario } from '../../../../../../main/webapp/app/entities/funcionario/funcionario.model';

describe('Component Tests', () => {

    describe('Funcionario Management Detail Component', () => {
        let comp: FuncionarioDetailComponent;
        let fixture: ComponentFixture<FuncionarioDetailComponent>;
        let service: FuncionarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [FuncionarioDetailComponent],
                providers: [
                    FuncionarioService
                ]
            })
            .overrideTemplate(FuncionarioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(FuncionarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FuncionarioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Funcionario(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.funcionario).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
