from flask import *
from database import*

import random

import smtplib
from email.mime.text import MIMEText
from flask_mail import Mail


public=Blueprint('public',__name__)

@public.route('/')
def public_home():

	return render_template('public_home.html')

@public.route('/public_login',methods=['post','get'])
def public_login():
	if "login" in request.form:
		u=request.form['uname']
		p=request.form['pwd']
		q="select * from login where username='%s' and password='%s'"%(u,p)
		res=select(q)

		if res:
			session['login_id']=res[0]['login_id']
			lid=session['login_id']

			if res[0]['usertype']=="admin":
				return redirect(url_for('admin.admin_home'))
			elif res[0]['usertype']=="company":
				q="select * from company where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['company_id']=res[0]['company_id']
					cid=session['company_id']
				return redirect(url_for('company.company_home'))
		


		else:
			flash('invalid username and password')
					
	
					

		
	return render_template('public_login.html')


@public.route('/company_registration',methods=['post','get'])	
def company_registration():
	if "cusreg" in request.form:
		f=request.form['fname']
	
		p=request.form['place']
	
		n=request.form['num']
		e=request.form['email']
		u=request.form['uname']
		pa=request.form['pwd']


		q="select * from login where username='%s' and password='%s'"%(u,pa)
		res=select(q)
		if res:

			flash('already exist')

		else:
			
			q="insert into login values(null,'%s','%s','company')"%(u,pa)
			id=insert(q)
			q="insert into company values(null,'%s','%s','%s','%s','%s')"%(id,f,p,n,e)
			insert(q)
			print(q)
			
			flash('Registered successfully')
			return redirect(url_for('public.company_registration'))

	return render_template('company_registration.html')


@public.route('/forgotpassword',methods=['get','post'])
def forgotpassword():
	data={}
	if 'next' in request.form:
		ph=request.form['ph']
		uname=request.form['uname']
		q="select email,username from login inner join company using(login_id) where username='%s' and phone='%s' "%(uname,ph)
		print(q)
		res=select(q)
		print(res)
		if res:
			print(res)
			session['uname']=res[0]['username']
			# email=res[0]['email']
			email=res[0]['email']
			print(email)
			rd=random.randrange(1000,9999,4)
			msg=str(rd)
			data['rd']=rd
			print(rd)
			try:
				gmail = smtplib.SMTP('smtp.gmail.com', 587)
				gmail.ehlo()
				gmail.starttls()
				gmail.login('projectsriss2020@gmail.com','vroiyiwujcvnvade')
			except Exception as e:
				print("Couldn't setup email!!"+str(e))

			msg = MIMEText(msg)

			msg['Subject'] = 'OTP FOR PASSWORD RECOVRY'

			msg['To'] = email

			msg['From'] = 'projectsriss2020@gmail.com'

			try:

				gmail.send_message(msg)
				print(msg)
				flash("EMAIL SENED SUCCESFULLY")
				session['rd']=rd
				return redirect(url_for('public.enterotp'))


			except Exception as e:
				print("COULDN'T SEND EMAIL", str(e))
				return redirect(url_for('public.forgotpassword'))
		else:
			flash("INVALID DETAILS")
			return redirect(url_for('public.forgotpassword'))
	return render_template("forgotpassword.html",data=data)



@public.route('/enterotp',methods=['get','post'])
def enterotp():
	rd=session['rd']
	uname=session['uname']
	data={}
	if "otp" in request.form:
		otp=request.form['otp']
		if int(otp)==int(rd):
			data['chp']=uname
		else:
			flash("invalid otp")
			return redirect(url_for('public.forgotpassword'))


	if 'update' in request.form:
		uname=request.form['uname']
		p=request.form['pwd']
		cp=request.form['pwds']
		if p==cp:
			print("+++++++++++")
			q="update login set password='%s' where username='%s'"%(p,uname)
			update(q)

			print(q)
			flash("UPDATED SUCCESSFULLY")
			return redirect(url_for('public.public_login'))
		else:
			flash("PASSWORD MISMATCH")
			data['chp']=uname
	return render_template("enterotp.html",data=data)

		

