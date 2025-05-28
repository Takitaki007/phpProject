"use client";
import { Button } from "@/components/ui/button";

import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { KeyRound, Mail, UserRound } from "lucide-react";
import Link from "next/link";
import React, { useState } from "react";

export default function RegisterComponent() {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send form data to the server-side API route
      const response = await fetch("/api/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData), // Send the form data to the API
      });

      const data = await response.json();

      if (response.ok) {
        setMessage("Registration successful!");
      } else {
        setMessage(data.error || "Something went wrong.");
      }
    } catch (error) {
      setMessage("Failed to connect to the server.");
    }
  };

  return (
    <form className="space-y-6" onSubmit={handleSubmit}>
      {/* username */}
      <div>
        <Label
          htmlFor="username"
          className="text-light-steel-blue flex gap-2 items-start mb-2 text-base"
        >
          <UserRound size={20} /> Username
        </Label>
        <Input
          type="text"
          name="username"
          placeholder="Please type your username"
          value={formData.username}
          onChange={handleChange}
          required
          className="bg-ghost-white py-2.5 px-4 rounded-lg w-full text-light-steel-blue/90"
        />
      </div>

      {/* email */}
      <div>
        <Label
          htmlFor="email"
          className="text-light-steel-blue flex gap-2 items-start mb-2 text-base"
        >
          <Mail size={20} /> Email
        </Label>
        <Input
          type="email"
          name="email"
          placeholder="Please type your email"
          value={formData.email}
          onChange={handleChange}
          required
          className="bg-ghost-white py-2.5 px-4 rounded-lg w-full text-light-steel-blue/90"
        />
      </div>

      {/* password */}
      <div>
        <Label
          htmlFor="password"
          className="text-light-steel-blue flex gap-2 items-start mb-2 text-base"
        >
          <KeyRound size={20} /> Password
        </Label>
        <Input
          type="password"
          name="password"
          placeholder="Please type your password"
          value={formData.password}
          onChange={handleChange}
          required
          className="bg-ghost-white py-2.5 px-4 rounded-lg w-full text-light-steel-blue/90"
        />
      </div>

      {/* Sign Up Button */}
      <Button
        type="submit"
        className="text-base cursor-pointer bg-persian-green text-white py-2.5 rounded-lg w-full font-bold"
      >Sign Up</Button>

      {/* Message */}
      {message && <p className="text-blue-500 text-center mt-2">{message}</p>}

      {/* Already have an account? Link */}
      <div className="text-right mt-2 font-normal">
        Already have an account?{" "}
        <Link
          href="/login"
          className="hover:text-persian-green hover:underline"
        >
          Login
        </Link>
      </div>
    </form>
  );
}
