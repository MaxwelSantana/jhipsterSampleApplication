import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Funcionario } from './funcionario.model';
import { FuncionarioService } from './funcionario.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-funcionario',
    templateUrl: './funcionario.component.html'
})
export class FuncionarioComponent implements OnInit, OnDestroy {
funcionarios: Funcionario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private funcionarioService: FuncionarioService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.funcionarioService.query().subscribe(
            (res: HttpResponse<Funcionario[]>) => {
                this.funcionarios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInFuncionarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Funcionario) {
        return item.id;
    }
    registerChangeInFuncionarios() {
        this.eventSubscriber = this.eventManager.subscribe('funcionarioListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
