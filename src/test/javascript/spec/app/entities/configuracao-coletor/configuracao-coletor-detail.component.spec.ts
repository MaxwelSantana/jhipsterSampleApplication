/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ConfiguracaoColetorDetailComponent } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor-detail.component';
import { ConfiguracaoColetorService } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.service';
import { ConfiguracaoColetor } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.model';

describe('Component Tests', () => {

    describe('ConfiguracaoColetor Management Detail Component', () => {
        let comp: ConfiguracaoColetorDetailComponent;
        let fixture: ComponentFixture<ConfiguracaoColetorDetailComponent>;
        let service: ConfiguracaoColetorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ConfiguracaoColetorDetailComponent],
                providers: [
                    ConfiguracaoColetorService
                ]
            })
            .overrideTemplate(ConfiguracaoColetorDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracaoColetorDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracaoColetorService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ConfiguracaoColetor(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.configuracaoColetor).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
