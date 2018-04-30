/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SecaoComponent } from '../../../../../../main/webapp/app/entities/secao/secao.component';
import { SecaoService } from '../../../../../../main/webapp/app/entities/secao/secao.service';
import { Secao } from '../../../../../../main/webapp/app/entities/secao/secao.model';

describe('Component Tests', () => {

    describe('Secao Management Component', () => {
        let comp: SecaoComponent;
        let fixture: ComponentFixture<SecaoComponent>;
        let service: SecaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [SecaoComponent],
                providers: [
                    SecaoService
                ]
            })
            .overrideTemplate(SecaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SecaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SecaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Secao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.secaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
